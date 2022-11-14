package com.gxa.modules.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gxa.modules.order.dto.OrderListDto;
import com.gxa.modules.order.dto.RefundDetailDto;
import com.gxa.modules.order.dto.RefundListDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
@Mapper
public interface RefundMapper extends BaseMapper<RefundListDto> {
    Page<RefundListDto> queryRefundOrderByPage(Page<RefundListDto> refundListDtoPage, @Param("refundStatus") String refundStatus, @Param("orderNo") String orderNo, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
    RefundDetailDto queryRefundDetailByApplicationNo(String applicationNo);
    void updateRefundStatusByApplicationNo(@Param("applicationNo") String applicationNo,@Param("refundMark") String refundMark,@Param("refundStatus") String refundStatus,@Param("orderStatus") String orderStatus);
}
