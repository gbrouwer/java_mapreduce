function pagerank

%Init
clc;
beta = 0.85;
load data.mat;


%Page rank
V = v;
for i=1:10
  v = ((beta*T)*v) + ((1-beta)/size(T,1));
  V = [V v];
end

imagesc(V);
V