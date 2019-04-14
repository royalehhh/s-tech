package com.stk.website.dto;

import com.stk.website.dao.model.TempFile;
import com.stk.website.dto.inner.BaseResponse;
import lombok.Data;

@Data
public class FileResponse extends BaseResponse {

    TempFile tempFile;
}
