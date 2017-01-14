function [x,bv,cost]=p2simplexS(c,A,b,piv)
%p2simplex(c,A,b,piv) solves the following linear problem applying
%Two-Phase Simplex Method:
%                   minimize    c'x
%                   subject to  Ax=b
%                               x>=0 .
%Matrix A does not have to be in canonical form.
%
%Supplement program for the course Advanced Algorithms and Data Structures at the
%Faculty of Electrical Engineering and Computing, University of Zagreb, Croatia.
%
% *** Nikica Hlupic, February, 2009 ***
%Last revision: November, 2011
%
%INPUT: c = column vector of cost function coefficients of (all, including
%           nonbasic) decision variables
%       A = matrix of left side coefficients in constraint equations
%       b = column vector of right side coefficients in constraint equations
%       piv = optional; specifies how the pivot element is selected
%           0 = false = choose the most negative relative cost coefficient (default);
%           1 = true = apply Bland's rule.
%OUTPUT: x = column vector of optimal values of decision variables in the order
%               in which they figure in A (like if they were extracetd from A)
%        bv = column vector of indices of base vectors in the final A-matirx
%               (upon the algorithm has finished)
%        cost = minimal possible value, subject to constraints, of the cost function c'x.
%REMARK: To construct artifical system, this function simply augments the
%   original system by identity matrix Im. However, if any column in A is
%   unit vector and as such is one of vectors in canonical base, it will be
%   duplicated, which will probably cause the algorithm to fail (diverge).
%   This must be circumvented manually.

if nargin < 4
    piv = 0;                    %default method of selecting the pivot
  if nargin < 3
    disp('Insufficient number of arguments.');
    return;
  end 
end 

n=length(c);                    %Number of decision variables.
m=length(b);                    %Number of constraints.

%Construction of artifical problem. We introduce m artifical variables.
artA=[A eye(m)];                %Artifical A; Im for artifical variables added to the right.
artc=[zeros(n,1); ones(m,1)];   %Aritifical cost function coefficients
                                %(=1 for all artifical variables, =0 else).
artbv=(n+1):(n+m);              %Indices of base columns in artA (= artifical columns).

% %Solving Example 1 for the course AADS...
% %The last three original columns (due to slack variables) are suitable for
% %canonical base, so we have to include them in the initial base when solving the
% %artifical problem.
% temp=eye(m);                        %Identity matrix Im.
% artA=[A temp(:,1:m-3)];             %(artifical A) = (original A) + (identity matrix without three last columns)
% artc=[zeros(n,1); ones(m-3,1)];     %artifical cost function (=sum of artifical var.)
% artbv=(n-3+1):(n-3+m);              %indices (in artA) of base vectors in artifical problem

%Phase 1: solving the artifical problem.
disp('Solving the artifical problem:');
[x,bv,cost] = simplexS(artc,artA,b,artbv,piv);
% [x,bv,cost] = simplex(artc,artA,b(1:6,:),artbv,piv);   %!

%Phase 2: solving the original problem, now knowing which columns should be
%treated as initial basis (basic solution).
%Construction of canonical augmented matrix for the original problem.
Aaug = [A b];               %Augmented A.
B = A(:,bv(:));             %Basic columns in A. Vector 'bv' overtaken from the first phase.
canAaug = B\Aaug;   %canAaug = inv(B)*Aaug; canonical augmented A for basis given by 'bv'
%Solving the original problem.
disp('Solving the original problem:');
[x,bv,cost] = csimplexS(c,canAaug(:,1:n),canAaug(:,n+1),bv,piv);
end
