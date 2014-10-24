function createTable


%Init
clc;
nElements = 100;
nColumns = 2;



%Union
a = floor(rand(nElements,nColumns)*5);
b = floor(rand(nElements,nColumns)*5);



%Write A and B
fid = fopen('table','w');
for i=1:nElements
  fprintf(fid,'a');
  for j=1:nColumns
    fprintf(fid,',%d',a(i,j));
  end
  fprintf(fid,'\n');
end
for i=1:nElements
  fprintf(fid,'b');
  for j=1:nColumns
    fprintf(fid,',%d',b(i,j));
  end
  fprintf(fid,'\n');
end
fclose(fid);