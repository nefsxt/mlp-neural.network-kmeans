load dataset.txt
load centroids.txt


figure(1);
hold on 
plot(dataset(:,1),dataset(:,2),"+");
plot(centroids(:,1),centroids(:,2),"*");
hold off;
