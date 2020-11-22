
-- Author: Victor A. Lora
-- AIC: 
-- Course: CSI-305
-- Date: 03/21/16
-- Description: SQL Exercises - Multi-Table

-- A.) 	How many copies of the book titled 'The Lost Tribe' are owned by the
--	library branch whose name is 'Sharpstown'?
SELECT 	No_Of_Copies
FROM 	Book_Copies bc
		INNER JOIN Book b 
			ON bc.Book_Id = b.Book_Id
		INNER JOIN Library_Branch lb 
			ON bc.Branch_id = lb.Branch_Id
WHERE 	Title = 'The Lost Tribe'
	AND Branch_Name = 'Sharpstown';

-- B.)	How many copies of the book titled 'The Lost Tribe' are owned by each
--	library branch. Display branch name and number of copies owned.
SELECT 	lb.Branch_Name, No_Of_Copies
FROM 	Book_Copies bc
		INNER JOIN Book b 
			ON bc.Book_Id = b.Book_Id
		INNER JOIN Library_Branch lb 
			ON bc.Branch_id = lb.Branch_Id
WHERE 	Title = 'The Lost Tribe';

-- C.)	Retrieve the names of all borrowers who do not have any books checked
--	out
SELECT 	Name
FROM	Borrower b
WHERE	NOT EXISTS (SELECT bl.Card_No
		    FROM Book_Loans bl 
		    WHERE b.Card_No = bl.Card_No);

-- D.)	For each book that is loaned from the Sharpstown branch and whose due
--	date is '2015-05-12' tetrieve the book title, the borrower's name and
--	the borrower's address. Display the book title, borrower name and
--	borrower address.
SELECT 	Book.Title, b.Name, b.Address
FROM 	Book_Loans bl
		INNER JOIN Book 
			ON Book.Book_Id = bl.Book_Id
		INNER JOIN Borrower b 
			ON b.Card_No = bl.Card_No
		INNER JOIN Library_Branch lb 
			ON lb.Branch_Id = bl.Branch_Id
WHERE 	lb.Branch_Name = 'Sharpstown'
	AND bl.Due_Date = '2015-05-12';

-- E.)	For each library branch, retrieve the branch name and the total number
--	of books loaned out from that branch.
SELECT 	lb.Branch_Name, count(bl.Book_Id)
FROM 	Book_Loans bl
		INNER JOIN Library_Branch lb 
			ON lb.Branch_Id = bl.Branch_Id
GROUP BY lb.Branch_Name;

-- F.)	Retrieve the names, addresses, and number of books checked out for all
--	borrowers who have more than two books checked out.
SELECT 	b.Name, b.Address, count(bl.Book_Id) as No_Of_Borrowed_Books
FROM 	Book_Loans bl
		INNER JOIN Borrower b 
			ON bl.Card_No = b.Card_No
GROUP BY bl.Card_No
HAVING No_Of_Borrowed_Books > 2;

-- G.)	For each book authored by Stephen King, retrieve the title and the
--	number of copies owned by the library branch whose name is Central.
SELECT 	b.Title, lb.Branch_Name, No_Of_Copies
FROM 	Book_Copies bc
		INNER JOIN Book b 
			ON bc.Book_Id = b.Book_Id
		INNER JOIN Library_Branch lb 
			ON bc.Branch_id = lb.Branch_Id
		INNER JOIN Book_Authors ba 
			ON bc.Book_Id = ba.Book_Id
WHERE 	ba.Author_Name = 'Stephen King'
	AND lb.Branch_Name = 'Central';

-- H.)	Retrieve the names of each book borrowed, the name of the branch from 
--	which it is borrowed, and the name of the borrower. Order by Branch 
--	name, then book title, then borrower name.
SELECT 	b.Title, lb.Branch_Name, bwr.Name
FROM 	Book_Loans bl
		INNER JOIN Book b 
			ON bl.Book_Id = b.Book_Id
		INNER JOIN Library_Branch lb 
			ON bl.Branch_id = lb.Branch_Id
		INNER JOIN Borrower bwr 
			ON bl.Card_No = bwr.Card_No
ORDER BY b.Title, lb.Branch_Name, bwr.Name;

-- I.)	How many copies of the book titled Grapes of Wrath are owned by each 
--	library branch. Display the book title, branch name, and number of 
--	copies owned. Some branches may have no copies, so display 0 as the 
--	copy count for these.
SELECT 	b.Title, lb.Branch_Name, No_of_Copies
FROM 	Book_Copies bc
		INNER JOIN Book b 
			ON bc.Book_Id = b.Book_Id
		INNER JOIN Library_Branch lb 
			ON bc.Branch_id = lb.Branch_Id
WHERE 	b.Title = 'Grapes of Wrath'
UNION
SELECT 	'Grapes of Wrath', lb.Branch_Name, 0
FROM 	Book_Copies bc
		INNER JOIN Library_Branch lb 
			ON bc.Branch_id = lb.Branch_Id
WHERE 	lb.Branch_Id NOT IN (SELECT lb.Branch_Id
			     FROM Book_Copies bc
					  INNER JOIN Library_Branch lb 
						ON lb.Branch_Id = bc.Branch_Id
				          INNER JOIN Book b 
						ON bc.Book_Id = b.Book_Id
		             WHERE b.Title = 'Grapes of Wrath');


