package run.ouo.app.model.vo.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BoolOptionVO {
    @NotNull
    public Boolean option;
}
