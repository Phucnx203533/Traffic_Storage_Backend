package aithings.camAI.controlpanel.utils.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ActionType {
    LOGIN("LOGIN", "Đăng nhập"),
    LOGOUT("LOGOUT", "Đăng xuất"),
    ADD("ADD", "Thêm mới"),
    EDIT("EDIT", "Cập nhật"),
    DELETE("DELETE", "Xóa"),
    LOCK("LOCK", "Khóa"),
    UNLOCK("UNLOCK", "Mở khóa"),
    APPROVE("APPROVE", "Duyệt"),
    GET("GET", "Lấy dữ liệu"),
    REJECT("REJECT", "Từ chối");

    private String value;
    private String name;

    public void init() {

    }

    public static String getName(String value) {
        for (ActionType actionType : ActionType.values()) {
            if (actionType.getValue().equals(value)) {
                return actionType.getName();
            }
        }
        return value;
    }

    public static List<ActionType> getAll() {
        return List.of(ActionType.values());
    }


}
