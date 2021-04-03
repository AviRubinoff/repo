/**
 * A class for an element in a course database
 * @author Avi Rubinoff
 */
public class CourseDBElement
{
    private String courseID;
    private int crn;
    private int credits;
    private String roomNumber;
    private String instructor;

    /**
     * Default constructor - puts default in each field, so you should call the various setters if you
     * intend to actually use the object for something. (Or better yet, just use the other constructor.)
     */
    public CourseDBElement()
    {
        this("DefaultCourseID", 0, 0, "Default Room Number", "Default Instructor");
    }

    /**
     * Constructor method.
     *
     * @param courseIdIn   the ID of the course.
     * @param crnIn        the CRN - unique to each course.
     * @param creditsIn    the number of credits the course is worth.
     * @param roomNumberIn the location of the course.
     * @param instructorIn the instructor.
     */
    public CourseDBElement(String courseIdIn, int crnIn, int creditsIn, String roomNumberIn, String instructorIn)
    {
        courseID = courseIdIn;
        crn = crnIn;
        credits = creditsIn;
        roomNumber = roomNumberIn;
        instructor = instructorIn;
    }

    /**
     * Compares a given CourseDBElement to this CourseDBElement via their CRNs.
     *
     * @param element the other CourseDBElement
     * @return a positive integer if this CourseDBElement has a higher CRN; a negative integer if element has a higher CRN; 0 if the two CRNs are equal.
     */
    public int compareTo(CourseDBElement element)
    {
        return this.crn - element.getCRN();
    }

    @Override
    public int hashCode()
    {
        String hashString = "" + crn;
        return hashString.hashCode();
    }

    @Override
    public String toString()
    {
        return "\nCourse:" + courseID + " CRN:" + crn + " Credits:" + credits + " Instructor:" + instructor + " Room:" + roomNumber;
    }

    /**
     * Sets the CRN to the specified value.
     * @param crnIn the new CRN.
     */
    public void setCRN(int crnIn)
    {
        crn = crnIn;
    }

    /**
     * Getter method.
     * @return the course ID.
     */
    public String getCourseID()
    {
        return courseID;
    }

    /**
     * Getter method.
     * @return the CRN.
     */
    public int getCRN()
    {
        return crn;
    }

    /**
     * Getter method.
     * @return the number of credits the course is worth.
     */
    public int getCredits()
    {
        return credits;
    }

    /**
     * Getter method.
     * @return the location the course is taught.
     */
    public String getRoomNumber()
    {
        return roomNumber;
    }

    /**
     * Getter method.
     * @return the name of the instructor.
     */
    public String getInstructor()
    {
        return instructor;
    }
}
