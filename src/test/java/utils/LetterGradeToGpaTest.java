package utils;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LetterGradeToGpaTest{

    @Test
    public void indexZeroReturnsFourGpa(){
        assertEquals(4.0, LetterGradeToGpa.getGpaPerClass(0));
    }

    @Test
    public void indexOneReturnsThreePointSevenGpa(){
        assertEquals(3.7, LetterGradeToGpa.getGpaPerClass(1));
    }
}