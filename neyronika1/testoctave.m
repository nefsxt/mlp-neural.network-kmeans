load c1.txt
load c2.txt
load c3.txt
load c4.txt
load dataset.txt

figure (1);
hold on
plot(c1(:,1),c1(:,2),"+")
plot(c2(:,1),c2(:,2),"+")
plot(c3(:,1),c3(:,2),"+")
plot(c4(:,1),c4(:,2),"+")
hold off

figure (2);
hold on
plot(dataset(:,1),dataset(:,2),"+")
hold off