import math
import sympy

def f(x):
	return 4**(sympy.sin(6))
	
def silnia(n):
	s=1
	for i in range(2,n+1):
		s*=i
	return s

def simpson(a, b, n):
	x1=0.0
	x2=0.0
	x0=f(a)+f(b)
	h=(b-a)/n
	wynik=0.0
	
	for i in range(1,n):
		x=(a+(i*h))
		if i%2==0:
			x2+=f(x)
		else:
			x1+=f(x)
	wynik=h*(x0+2*x2+4*x1)/3
	return wynik

print(float(simpson(8,2,4)))