def simpson(function, a, b, n):
x=[a]
y=[]
h=(b-a)/n
wynik=0

for i in range(1,n):
	x.append(x[0]+(i*h))

for i in range(0,n):
	y.append(f(x[i]))
		if i==0 or i==n:
			wynik+=y[i]
		else:
			if i%2==0:
				wynik += 2*y[i]
			else:
				wynik += 4*y[i]
				
wynik *= h/3
return wynik