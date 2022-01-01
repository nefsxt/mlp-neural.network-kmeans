#load c1.txt
#load c2.txt
#load c3.txt
#load c4.txt
#load dataset.txt
load Errors.txt

figure (1);
hold on
#plot(c1(:,1),c1(:,2),"+")
#plot(c2(:,1),c2(:,2),"+")
#plot(c3(:,1),c3(:,2),"+")
#lot(c4(:,1),c4(:,2),"+")
plot(Errors(:,2),Errors(:,1),"+")
hold off

figure (1);
hold on
#plot(c1(:,1),c1(:,2),"+")
#plot(c2(:,1),c2(:,2),"+")
#plot(c3(:,1),c3(:,2),"+")
#lot(c4(:,1),c4(:,2),"+")
plot(Errors(:,2),Errors(:,1),"+")
hold off