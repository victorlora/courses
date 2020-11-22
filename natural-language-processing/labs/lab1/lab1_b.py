def main ():
	filename = "user_input.txt"
	file = open(filename, 'r')
	columns = create_column_dict(file.readline())
	super_list = []

	for line in file:
		super_list.append(create_row(line))

	test1 = "dd"

	final_state = 2
	starting_row = 0
	row = starting_row

	failed = False

	for char in test1:
		val = super_list[row][columns.get(char, -1)]
		val = int(val)
		if val != -1:
			row = val
		else:
			failed = True
			break

	if not failed and row == final_state:
		print "Valid"
	else:
		print "Fail"

def create_column_dict(user_input):
	column_dict = dict()
	counter = 0
	for word in user_input.split():
		column_dict[word] = counter
		counter += 1
	return column_dict

def create_row(line):
	row = []
	for char in line.split():
		row.append(char)
	return row


main()