package com.ws.ldy.modules.admin.service.impl;

import com.ws.ldy.modules.admin.mapper.DictionaryAdminMapper;
import com.ws.ldy.modules.admin.model.entity.DictionaryAdmin;
import com.ws.ldy.modules.admin.model.vo.DictionaryAdminVo;
import com.ws.ldy.modules.admin.service.DictionaryAdminService;
import com.ws.ldy.others.base.service.impl.BaseIServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class DictionaryAdminServiceImpl extends BaseIServiceImpl<DictionaryAdminMapper, DictionaryAdmin> implements DictionaryAdminService {


    @Override
    public DictionaryAdminVo findCode(String code) {
        List<DictionaryAdminVo> dictList = baseMapper.findCode(code);
        //找到顶级
        DictionaryAdminVo dictVO = new DictionaryAdminVo();
        for (DictionaryAdminVo dict : dictList) {
            if (code.equals(dict.getCode())) {
                dictVO = dict;
                //自己
                break;
            }
        }
        //遍历子级，最多两级，2层for
        for (DictionaryAdminVo dictOne : dictList) {
            //第一层
            if (dictOne.getPid().equals(dictVO.getId())) {
                //没有就创建/有就追加
                if (dictVO.getDictList() == null) {
                    dictVO.setDictList(new ArrayList<DictionaryAdminVo>() {{
                        add(dictOne);
                    }});
                } else {
                    dictVO.getDictList().add(dictOne);
                }
                //第二层
                for (DictionaryAdminVo dictTwo : dictList) {
                    //没有就创建/有就追加
                    if (dictTwo.getCode().equals(dictOne.getId())) {
                        if (dictOne.getDictList() == null) {
                            dictOne.setDictList(new ArrayList<DictionaryAdminVo>() {{
                                add(dictTwo);
                            }});
                        } else {
                            dictOne.getDictList().add(dictOne);
                        }
                    }
                }
            }
        }
        return dictVO;
    }
}

