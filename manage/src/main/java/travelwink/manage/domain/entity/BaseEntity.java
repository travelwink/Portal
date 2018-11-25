package travelwink.manage.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BaseEntity {
    private String createBy;

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date createDate;

    private String updateBy;

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date updateDate;

    private int status;
}
