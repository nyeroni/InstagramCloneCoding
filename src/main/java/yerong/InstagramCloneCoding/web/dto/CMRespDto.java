package yerong.InstagramCloneCoding.web.dto;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CMRespDto<T>{
    private int code; //1(성공), -1(실패)
    private String message;
    private T data;

}
