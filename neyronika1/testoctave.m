load ("dataset1withExamples.txt");
load Errors.txt



figure (1);
hold on
#plot3(dataset1withExamples(:,1),dataset1withExamples(:,3),dataset1withExamples(:,2),"+")
plot(Errors(:,2),Errors(:,1),"linestyle", "-")
hold off