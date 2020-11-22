import re
ID =0
flag = False
with open("cran.all.1400") as f:
    for line in f:
	m = re.search(r'(\.I)(.*)', line)
	if m is not None:
		if ID != m.group(2):
			if ID > 0:
				fo.close()
			ID = m.group(2).strip()
			fo = open(ID+".analysis", "wb")
			flag = True
	if flag == True:
		fo.write(line)
