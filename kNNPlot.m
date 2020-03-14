A = Column1;
B = Column2;
C = Column3;

x = A(101)
y = B(101)

plot(x,y,'x','Color','k','linewidth',2);

hold on

axis([-5 5 -5 5]);
pbaspect([1 1 1]);

for i = 1:101
    if C(i) == 0
        plot(A(i),B(i),'x','Color','k','linewidth',2);
    elseif C(i) == 1
        plot(A(i),B(i),'x','Color','r','linewidth',2);
    elseif C(i) == 2
        plot(A(i),B(i),'x','Color','b','linewidth',2);
    elseif C(i) == 3
        plot(A(i),B(i),'x','Color','g','linewidth',2);
    elseif C(i) == 4
        plot(A(i),B(i),'x','Color','b','linewidth',2);
    end
end

%plot(x,y,'x','Color','r','linewidth',2);
circle(A(101),B(101),1.4);

function circle(x,y,r)
ang=0:0.01:2*pi; 
xp=r*cos(ang);
yp=r*sin(ang);
plot(x+xp,y+yp);
end