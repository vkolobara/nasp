function [x,bv,cost]=simplexS(c,A,b,bv,piv)
%simplex(c,A,b,bv,piv) solves the following linear problem
%                   minimize    c'x
%                   subject to  Ax=b
%                               x>=0
%applying Simplex Method provided that the initial basic variables (base columns
%of the constraint matrix A) are known.
%However, matrix A does not have to be in canonical form.
%
%Supplement program for the course Advanced Algorithms and Data Structures at the
%Faculty of Electrical Engineering and Computing, University of Zagreb, Croatia.
%
% *** Nikica Hlupic, February, 2009 ***
%Last revision: November, 2011
%
%INPUT: c = column vector of cost function coefficients of (all, including
%           nonbasic) decision variables
%       A = matrix of the left side coefficients in constraint equations
%       b = column vector of the right side coefficients in constraint equations
%       bv = vector of indices of basic columns (vectors) in A.
%           Specifically, the i-th base vector is bv(i)-th column of A.
%       piv = optional; specifies how the pivot element is selected
%           0 = false = choose the most negative relative cost coefficient (default);
%           1 = true = apply Bland's rule.
%OUTPUT: x = column vector of optimal values of decision variables in the order
%               in which they figure in A (like if they were extracetd from A)
%        bv = column vector of indices of base vectors in the final A-matrix
%               (upon the algorithm has finished)
%        cost = minimal possible value, subject to constraints, of the cost function c'x.
%REMARK: the difference in comparison to csimplex() is that matrix A does not
%   have to be in canonical form. Othervise, the purpose of these two functions
%   is the same. In fact, simplex() just constructs canonical form of A and then
%   calls csimplex() to solve the problem.

if nargin < 5
    piv = 0;                    %default method of selecting the pivot
  if nargin < 4
    disp('Insufficient number of arguments.');
    return;
  end 
end 

n=length(c);                    %Number of decision variables.

Aaug = [A b];                   %Augmented A.
B = A(:,bv(:));                 %Basic columns in A.
canAaug = B\Aaug;               %Canonical augmented A: canAaug = inv(B)*Aaug.
newA = canAaug(:,1:n);          %New A to pass to csimplex().
newb = canAaug(:,n+1);          %New b to pass to csimplex() = last column in canAaug.

[x,bv,cost] = csimplexS(c,newA,newb,bv,piv);     %Solve it!
end
