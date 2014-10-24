function matrixmultiplication


%Create Data
clc;
A = round(rand(10,10)*10);
B = round(rand(10,50)*10);



%Standard
for i=1:size(A,1)
  for j=1:size(B,2)
    R(i,j) = sum(A(i,:) .* B(:,j)');
  end
end



%Flatten
list = [];
for i=1:size(A,1)
  for j=1:size(A,2)
    list = [list ; 100 i-1 j-1 A(i,j)];
  end
end
for i=1:size(B,1)
  for j=1:size(B,2)
    list = [list ; 200 i-1 j-1 B(i,j)];
  end
end



%Map Phase
m=size(A,1);
n=size(A,2);
p=size(B,2);
emits = [];
for e=1:size(list,1)

  if (list(e,1) == 100)
    i = list(e,2);
    j = list(e,3);
    v = list(e,4);
    for k=1:p
      emits = [emits; i k-1 100 j v];
    end
    
  else
    j = list(e,2);
    k = list(e,3);
    v = list(e,4);
    for i=1:m
      emits = [emits ; i-1 k 200 j v];
    end
  end

end



%Reduce Phase
keys = unique(emits(:,1:2),'rows');
for k=1:size(keys,1)
  val1 = keys(k,1);
  val2 = keys(k,2);
  dum1 = (emits(:,1) == val1);
  dum2 = (emits(:,2) == val2);
  dum = dum1 + dum2;
  dum = dum == 2;
  thisemits = emits(dum,:);
  
  dum1 = (thisemits(:,3) == 100);
  dum2 = (thisemits(:,3) == 200);
  values1 = thisemits(dum1,end);
  values2 = thisemits(dum2,end);
  V = sum(values1.*values2);
  O(val1+1,val2+1) = V;
end

O
A*B

