package com.gxa.modules.fristpage.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsInfodto {
    @ApiModelProperty("轮播图图片地址")
    private String imageUrl;
    @ApiModelProperty("价格")
    private Double price;
    @ApiModelProperty("总销量")
    private Integer num;
    @ApiModelProperty("药品名字")
    private String name;
    @ApiModelProperty("是否处方药")
    private String rx;
    @ApiModelProperty("规格")
    private String spec;
    @ApiModelProperty("主治功能")
    private String mainFunction;
    @ApiModelProperty("用法用量")
    private String usage;
    @ApiModelProperty("批准文号")
    private String approvalNum;
    @ApiModelProperty("轮播图图片地址")
    private List<String> instructionsUrl = new ArrayList<>();
    @ApiModelProperty("说明书图片地址")
    private String detailsUrl;
   public GoodsInfodto(GoodsInfo goodsInfo){
        this.imageUrl=goodsInfo.getImageUrl();
        this.price=goodsInfo.getPrice();
        this.num=goodsInfo.getNum();
        this.name=goodsInfo.getRx();
        this.rx=goodsInfo.getImageUrl();
        this.mainFunction=goodsInfo.getMainFunction();
        this.usage=goodsInfo.getUsage();
        this.approvalNum=goodsInfo.getApprovalNum();
        String instructionsUrl1 = goodsInfo.getInstructionsUrl();
        String[] split = instructionsUrl1.split(",");
        for (int i =0;i<split.length;i++){

            System.out.println(split[i]+i);
            this.instructionsUrl.add(i,split[i]);
        }
        this.detailsUrl=goodsInfo.getDetailsUrl();

    }
}
