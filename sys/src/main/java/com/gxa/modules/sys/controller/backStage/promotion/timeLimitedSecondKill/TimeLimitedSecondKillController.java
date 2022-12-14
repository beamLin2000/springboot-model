package com.gxa.modules.sys.controller.backStage.promotion.timeLimitedSecondKill;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.gxa.common.utils.PageUtils;
import com.gxa.common.utils.Result;
import com.gxa.modules.goods.goodsService.DrugService;
import com.gxa.modules.login.entity.Data;
import com.gxa.modules.sys.entity.backStage.promotion.timeLimitedSecondKill.LimitedTimeFlashDeal;
import com.gxa.modules.sys.service.promotion.TimeLimitedSecondKillService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitTemplateConfigurer;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author :林溪
 * @date : 2022/11/11 14:11
 */
@RestController
@Api(tags = "后台:限时秒杀")
@RequestMapping("/timeLimitedSecondKill")
@Slf4j
public class TimeLimitedSecondKillController {

    @Autowired
    private TimeLimitedSecondKillService timeLimitedSecondKillService;
    @Autowired
    private DrugService drugService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/search")
    @ApiOperation(value = "筛选")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "drugName",value ="药品名称",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "page",value ="当前页",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "pageSize",value ="每一页显示的数据",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "status",value ="状态",dataType ="String")
    })
    public Result<PageUtils> search(@RequestParam @ApiIgnore Map<String,Object> map){
        PageUtils pageUtils = timeLimitedSecondKillService.queryPage(map);
        return new Result<PageUtils>().ok(pageUtils);
    }

    @GetMapping("/queryById")
    @ApiOperation(value = "查看/编辑Pre")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "id",value ="需要被查看/编辑的数据id,若为新增,则传递id值为0",dataType ="String")
    })
    public Result<LimitedTimeFlashDeal> queryById(@RequestParam(value = "id",defaultValue = "0") @ApiIgnore String id){
        if(id.equals("0")){
            LimitedTimeFlashDeal limitedTimeFlashDeal = new LimitedTimeFlashDeal();
            limitedTimeFlashDeal.setId(UUID.randomUUID().toString());
            return new Result<LimitedTimeFlashDeal>().ok(limitedTimeFlashDeal);
        }
        LimitedTimeFlashDeal limitedTimeFlashDeal = this.timeLimitedSecondKillService.queryById(id);
        return new Result<LimitedTimeFlashDeal>().ok(limitedTimeFlashDeal);
    }

    @DeleteMapping("/deleteById")
    @ApiOperation("根据id删除一条/多条数据")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body",name = "ids",value ="药品ids",dataType ="Array")
    })
    public Result deleteById(@RequestBody @ApiIgnore List<String> ids){
        if(ids.size()==0){
            return new Result().ok("由于传递数组长度为0,并没有删除任何数据");
        }
        Integer integer = this.timeLimitedSecondKillService.deleteById(ids);
        if(integer!=-1){
            return new Result().ok("删除成功");
        }
        return new Result().error("删除失败");
    }
    @ApiOperation("新增->选择商品/ ->请输入商品名")
    @GetMapping("/queryAllDrugInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "drugName",value = "商品名字",dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "page",value = "当前页",dataType = "Integer")
    })
    public Result<PageUtils> queryDrugInfo(@RequestParam @ApiIgnore Map<String,Object> map){
        PageUtils list = drugService.list(map);
        return new Result<PageUtils>().ok(list);
    }
    @PostMapping("/saveData")
    @ApiOperation("新增/保存编辑")
    public Result saveData(@RequestBody LimitedTimeFlashDeal limitedTimeFlashDeal){
        if(limitedTimeFlashDeal==null){
            return new Result().error("接收数据为空");
        }
        Integer success = null;
        LimitedTimeFlashDeal limitedTimeFlashDeal1 = timeLimitedSecondKillService.queryById(limitedTimeFlashDeal.getId());
        if(limitedTimeFlashDeal1!=null&&limitedTimeFlashDeal.getVersion()==limitedTimeFlashDeal1.getVersion()){
            try {
                //更新
                MessageProperties messageProperties = new MessageProperties();
                //时间转换
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                //Date start = simpleDateFormat.parse(limitedTimeFlashDeal.getActivityTimeStart());
                Date end = simpleDateFormat.parse(limitedTimeFlashDeal.getActivityTimeEnd());
                System.out.println();
                long time=end.getTime()-System.currentTimeMillis();
                //
                log.info("当前时间"+System.currentTimeMillis());
                log.info("结束时间"+end.getTime());
                log.info("时间差"+time);

                if(time<0){
                    return new Result().error("结束时间不能小于当前时间");
                }
                System.out.println("活动结束剩余时间:==============================="+time);
                messageProperties.setExpiration(String.valueOf(time));
                Message message = new Message(limitedTimeFlashDeal.getId().getBytes(),messageProperties);
                this.rabbitTemplate.convertAndSend("TimeKiller","TimeKillerQueue1",message);
                success = this.timeLimitedSecondKillService.updateData(limitedTimeFlashDeal);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return success==-1?new Result().error("编辑失败"):new Result().ok("编辑成功");
        }else if(limitedTimeFlashDeal1==null){
            //保存
            success = this.timeLimitedSecondKillService.saveData(limitedTimeFlashDeal);
            return success==-1?new Result().error("新增失败"):new Result().ok("新增成功");
        }
        return new Result().error("传递的数据不合法");

    }
        @RabbitListener(queues={"dead-queue01"})
        public void listener(Message message){
            String s = new String(message.getBody());
            timeLimitedSecondKillService.update(new UpdateWrapper<LimitedTimeFlashDeal>().set("`status`","已结束").eq("id",s));

        }
}
