package aithings.camAI.controlpanel.utils.constant;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
@Getter
public enum ETypeTraffic {
    PERSON(0,"Xe máy","person"),
    BICYCLE(1,"Xe đạp","bicycle"),
    CAR(2,"Xe ô tô","car"),
    MOTORCYCLE(3,"Xe máy","motorcyle"),
    BUS(4,"Xe buýt","bus"),
    TRUCK(5,"Xe tải","truck"),
    OTHER(7,"Khác","other");

    private final Integer value;
    final String nameVi;
    private final String nameEn;

    ETypeTraffic(Integer value, String nameVi, String nameEn){
        this.value = value;
        this.nameVi = nameVi;
        this.nameEn = nameEn;
    }

    public String getText(){
        return this.nameVi;
    }
    public static ETypeTraffic findByValue(Integer value){
        ETypeTraffic[] eTypeTraffics = ETypeTraffic.values();
        for(ETypeTraffic eTypeTraffic : eTypeTraffics){
            if(Objects.equals(eTypeTraffic.getValue(),value)){
                return eTypeTraffic;
            }
        }
        return null;
    }
    public static ETypeTraffic findByNameVi(String nameVi){
        ETypeTraffic[] eTypeTraffics = ETypeTraffic.values();
        for(ETypeTraffic eTypeTraffic : eTypeTraffics){
            if(Objects.equals(eTypeTraffic.getNameVi(),nameVi)){
                return eTypeTraffic;
            }
        }
        return null;
    }

    public static List<ETypeTraffic> getAll(){
        return Arrays.asList(ETypeTraffic.values());
    }
}

