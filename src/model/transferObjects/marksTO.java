package model.transferObjects;

/**
 * Transfer object for marks
 */
public class marksTO {
    public int roll;
    public String paperName;
    public String paperCode;
    public String paperType;
    public String half;
    public int FullMarks;
    public int ObtMarks;

    public marksTO(
        int roll,
        String paperName,
        String paperCode,
        String paperType,
        String half,
        int FullMarks,
        int ObtMarks)
        {
            this.roll = roll;
            this.paperCode = paperCode;
            this.paperName = paperName;
            this.paperType = paperType;
            this.half = half;
            this.FullMarks = FullMarks;
            this.ObtMarks = ObtMarks;
        }
}
