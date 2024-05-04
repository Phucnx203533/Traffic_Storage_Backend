package aithings.camAI.controlpanel.utils.constant;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Getter
public enum ETypeViolation {

    WRONG_LANE(0,"Sai làn đường","Wrong lane"),
    BLOW_RED_LIGHT(1,"Vượt đèn đỏ","Blow the red light"),
    OTHER(2,"Lỗi vi phạm khác","Other violation"),
    ;
    private final Integer value;
    final String nameVi;
    private final String nameEn;

    ETypeViolation(Integer value, String nameVi, String nameEn){
        this.value = value;
        this.nameVi = nameVi;
        this.nameEn = nameEn;
    }

    public String getText(){
        return this.nameVi;
    }
    public static ETypeViolation findByValue(Integer value){
        ETypeViolation[] eTypeViolations = ETypeViolation.values();
        for(ETypeViolation eTypeViolation : eTypeViolations){
            if(Objects.equals(eTypeViolation.getValue(),value)){
                return eTypeViolation;
            }
        }
        return null;
    }
    public static ETypeViolation findByNameVi(String nameVi){
        ETypeViolation[] eTypeViolations = ETypeViolation.values();
        for(ETypeViolation eTypeViolation : eTypeViolations){
            if(Objects.equals(eTypeViolation.getNameVi(),nameVi)){
                return eTypeViolation;
            }
        }
        return null;
    }

    public static List<ETypeViolation> getAll(){
        return Arrays.asList(ETypeViolation.values());
    }
}
