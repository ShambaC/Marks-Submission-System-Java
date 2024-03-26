package model.transferObjects;

/**
 * Transfer object for marks
 */
public class marksTO {
    public int roll;
    public String coll;
    public String cate;
    public String paperName;
    public String paperCode;
    public String paperType;
    public String half;
    public int FullMarks;
    public int ObtMarks;

    public marksTO(
        int roll,
        String coll,
        String cate,
        String paperName,
        String paperCode,
        String paperType,
        String half,
        int FullMarks,
        int ObtMarks)
        {
            this.roll = roll;
            this.coll = coll;
            this.cate = cate;
            this.paperCode = paperCode;
            this.paperName = paperName;
            this.paperType = paperType;
            this.half = half;
            this.FullMarks = FullMarks;
            this.ObtMarks = ObtMarks;
        }
}
