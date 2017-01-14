function Mnew = pivotS(M,p,q)
%Returns matrix Mnew resulting from pivoting the given matrix M about the
%(p,q)th element.
%
%Supplement program for the course Advanced Algorithms and Data Structures at the
%Faculty of Electrical Engineering and Computing, University of Zagreb, Croatia.

%*** Nikica Hlupic, February, 2009 ***
%Last revision: November, 2011

%Pivoting about the (p,q)th element is the process of Gauss-Jordan elimination
%of all, except p-th, entries in the q-th column of the matrix. Specifically,
%this means setting (p,q)th element to unity, and all other elements of q-th
%column to zero.
%The same matrix transformation can be achieved by elementary row operations,
%i.e., by left-multiplications of A by a sequence of elementary matrices (or
%multiplication by their total product).

for i = 1 : size(M,1)
    if i == p
        Mnew(p,:) = M(p,:) / M(p,q);
    else
        Mnew(i,:) = M(i,:) - M(p,:) * (M(i,q) / M(p,q));
    end
end
end
