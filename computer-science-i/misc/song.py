"""
	song.py

	A Program to display the song "Ten Bears"

	Author: Victor Lora

	Date: 1/23/14

	A.I.C.:____________________
	"""

def verse(number):
    print("%s bears in the bed and the little one said \"I'm crowded roll over.\"" %number)
    print("So, They all rolled over and fell out.")
    print("")

def line(number):
    print("%s bear in the bed and the little one said, \"You know what? I'm lonely\"" %number)

def main():
    verse("10")
    verse("9")
    verse("8")
    verse("7")
    verse("6")
    verse("5")
    verse("4")
    verse("3")
    verse("2")
    line("1")

main()
