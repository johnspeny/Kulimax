# Kulimax algorithm written by alan and speny


num, div = 1500, 3
l = [num // div + (1 if x < num % div else 0)  for x in range (div)]

# comodities
bread = 500
tea = 500
paste = 500

dic = [500, 500, 500]
q = []

for i in range(len(dic)):
	if dic[i] > l[i]:
		q.append("investiment capital too little")
	elif dic[i] <= l[i]:
		q.append(l[i]//dic[i])
print(q)


new_price = []
# total amount of items
for j in range(len(q)):
	new_price.append(q[j]*dic[j])

print(new_price)

land_size = sum(new_price) // sum(dic)
print("land size is sqmeters ", land_size)
