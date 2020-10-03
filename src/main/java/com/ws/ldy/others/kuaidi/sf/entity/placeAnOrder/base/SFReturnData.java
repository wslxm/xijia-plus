package com.ws.ldy.others.kuaidi.sf.entity.placeAnOrder.base;


import com.ws.ldy.others.base.model.Convert;
import lombok.Data;
import lombok.ToString;

/**
 * 顺丰请求的-统一返回数据格式   是否必传 Y-是
 * <P>
 * A1000	统一接入平台校验成功，调用后端服务成功;
 * 注意：
 *    不代表后端业务处理成功，实际业务处理结果，
 *    需要查看响应属性apiResultData中的详细结果
 * A1001	必传参数不可为空
 * A1002	请求时效已过期
 * A1003	IP无效
 * A1004	无对应服务权限
 * A1005	流量受控
 * A1006	数字签名无效
 * A1007	重复请求
 * A1008	数据解密失败
 * A1009	目标服务异常或不可达
 * A1099	系统异常
 * </P>
 */
@Data
@ToString
public class SFReturnData extends Convert {
    /**
     * apiResponseID	String(40)	Y	API响应唯一号UUID
     */
    private String apiResponseID;
    /**
     * apiResultCode	String(10)	Y	API平台结果代码
     */
    private String apiResultCode;
    /**
     *   apiErrorMsg	String(200)	N	API平台异常信息
     */
    private String apiErrorMsg;
    /**
     *  apiResultData	String	 N	业务处理详细结果
     */
    private String apiResultData;
}
