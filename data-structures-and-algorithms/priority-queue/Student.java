/*
 * CSI 220: Data Structures & Algorithms
 * @version 1.0 26 October 2015
 * @author Victor Lora
 * @description 
 *   Student Class
 *   
 */
public class Student implements Comparable<Student> {
	int year;
	String lastName;
	String firstName;

	/*
	 * Constructor for student.
	 * 
	 * Takes a class year, last name and a first name.
	 * 
	 */
	public Student(int year, String lastName, String firstName) {
		this.year = year;
		this.lastName = lastName;
		this.firstName = firstName;
	}
	
	/*
     * compareTo() compares this students class year to
     *             another students class year.
     *
     *             If this student has a has a lower class year year
     *             it has higher priority returns +1. Otherwise,
     *             return -1.
     *             If the students have equal class years, then they
     *             are to be sorted alphabetically by last name.
     *             If the students have the same class year and last
     *             name, then sort them by whoever was enqueued first.
     *             
     */         
    public int compareTo(Student student)
    {
        if (this.year > student.year)        	// This object has higher priority
            return 1;             				// ... so return +1.
        else if (this.year == student.year)   	// The object have equal priority
        	// ... so compare the students by last name. return +1 if this.lastname
        	// comes alphabetically before student.last
            return this.lastName.compareTo(student.lastName); 
        else                       				// This object has lower priority
            return -1;              			// ... so return -1.
    }
    
    /*
     * toString() returns a string composed of the class year,
     * 			  students last name and students first name.
     * 
     */
    public String toString()
    {
    	String print = this.year + ", " + this.lastName + ", " + 
    			this.firstName;
    	return print;
    }
}
