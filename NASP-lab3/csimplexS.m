function [x,bv,cost]=csimplexS(c,A,b,bv,piv)
%csimplex(c,A,b,bv,piv) solves the following canonical linear problem applying
%Simplex Method: 
%                   minimize    c'x
%                   subject to  Ax=b
%                               x>=0 .
%
%Supplement program for the course Advanced Algorithms and Data Structures at the
%Faculty of Electrical Engineering and Computing, University of Zagreb, Croatia.
%
% *** Nikica Hlupic, February, 2009 ***
%Last revision: November, 2011
%
%INPUT: c = column vector of cost function coefficients of (all, including
%           nonbasic) decision variables
%       A = matrix of left side coefficients of system of constraint equations
%           in canonical form A=[Im Y] (the order of columns is not important!)
%       b = column vector of the right side coefficients in constraint equations
%       bv = vector of indices of basic columns (vectors) in A.
%           Specifically, the i-th standard vector is bv(i)-th column of A.
%       piv = optional; specifies how the pivot element is selected
%           0 = false = choose the most negative relative cost coefficient (default);
%           1 = true = apply Bland's rule.
%OUTPUT: x = column vector of optimal values of decision variables in the order
%               in which they figure in A (like if they were extracetd from A)
%        bv = column vector of indices of base vectors in the final A-matrix
%               (upon the algorithm has finished)
%        cost = minimal possible value, subject to constraints, of the cost function c'x.

if nargin < 5
    piv = 0;                    %default method of selecting the pivot
  if nargin < 4
    disp('Insufficient number of arguments.');
    return;
  end 
end 

%format compact;
format rat;     %Display format: instead of decimal numbers, their rational
                %           approximations in the form of ratios are printed.
 
n=length(c);                %Number of decision variables.
m=length(b);                %Number of constraints.

cB=c(bv(:));                %Coefficients of base variables in cost-function.

%Construction of initial tableau.
%The below relation is valid only if A is in canonical form
%(contains all columns of identity matrix Im).
r = c'-cB'*A;   %Row vector of all relative cost coefficients; if A is in
%   canonical form, 'r' will have zeros at the positions of all basic variables.
cost = cB'*b;                   %Value of the objective (cost) function.
tabl=[A b; r -cost];            %Canonical tableau.
disp('Initial tableau:');           %Print tableau; just for control.
disp(tabl);                         %

% r(bv(:)) = 0;     %Not necessary (see the above comment) - just to make it explicit!
while min(r) < 0
    %Determine q; choose a non-base vector to become new base vector.
    if piv == true          %Bland's rule; q = the least index of a negative
        q = 1;              %'r', instead of index of the most negative 'r'.
        while r(q) >= 0;
            q=q+1;
        end
    else
        [minr,q] = min(r);  %q = index of the smallest (most negative) r-value.
    end
    %Determine p; choose a current base vector to be removed from the (current) base.
    minratio = inf;         %p = argmin(i) {yi0/yiq;	yiq > 0}
    p=0;
    for i=1:m
        if tabl(i,q)>0
            if tabl(i,n+1)/tabl(i,q) < minratio
                minratio = tabl(i,n+1)/tabl(i,q);  p = i;
            end
        end
    end
    if p == 0
        disp('Feasible set unbounded!');    %Not all variables are necessarily =inf,
        x = inf(n,1); return;               %but this is just an indication of failure.
    end
    tablnew=pivotS(tabl,p,q);        %New tableau; pivoting about (p,q).
    disp(['Pivot point: (',int2str(p),',',int2str(q),')']);     %Just for control.
    disp('New tableau:'); disp(tablnew);
    bv(p) = q;              %New base; vector q replaces vector p. p-th base vector = q-th vector in A.
    tabl=tablnew;           %Prepare for the next iteration.
    r = tabl(m+1,1:n);      %New reduced cost coefficients (after pivoting).
end %while

x = zeros(n,1);             %Solution: nonbasic variables = 0, values of basic variables are in
x(bv(:)) = tabl(1:m,n+1);   %the last column of tableau. Their indices in X are specified by 'bv'.
            %Here x contains values of decision variables in the order in which they figure in A.
cost = -tabl(m+1,n+1);      %Minimal possible value of cost function c'x.
end
