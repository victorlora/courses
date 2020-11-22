def main ():
	alphabet = { 'a':0, 'b':1, 'c':2 }

	row_1 = [1, -1, -1]
	row_2 = [1,  2,  0]
	row_3 = [-1, -1, 0]
	super_list = [row_1, row_2, row_3]

	test1 = "dd"

	final_state = 2
	starting_row = 0
	row = starting_row

	failed = False

	for char in test1:
		val = super_list[row][alphabet.get(char, -1)]
		if val != -1:
			row = val
		else:
			failed = True
			break

	if (not failed and row == final_state):
		print "Valid"
	else:
		print "Fail"

main()