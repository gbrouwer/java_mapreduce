function createData


%Init
clc;
nDim = 100;



%Generate
T = rand(nDim,nDim);
v = ones(nDim,1) ./ nDim;



%Normalize
for i=1:size(T,1)
  T(i,i) = 0;
  t = T(:,i);
  t = t ./ sum(t);
  T(:,i) = t;
end



%Store
save('data.mat','T','v');



%Write
fid = fopen('inputdata','w');
for i=1:nDim
  for j=1:nDim
    fprintf(fid,'A,%d,%d,%f\n',i-1,j-1,T(i,j));
  end
end
for i=1:nDim
  for j=1:1
    fprintf(fid,'B,%d,%d,%f\n',i-1,j-1,v(i,j));
  end
end
fclose(fid);




