package io.github.wslxm.springbootplus2.manage.gc.template;

/**
 *  @author wangsong
 * 复选Html 模板配置
 */
public interface VueMainTemplate {


    /**
     * 文本  {label}  {prop} {search}
     */
    String TEXT = "                {\n" +
            "                    label: '{label}',\n" +
            "                    prop: '{prop}',\n" +
            "                    search: {search},\n" +
            "                    searchSpan: 5,\n" +
            "                    overHidden: true,\n" +
            "                },\n";

    /**
     * 字典 (单选) {label}  {prop} {search}
     */
    String TEXT_DICT = "                {\n" +
            "                    label: '{label}',\n" +
            "                    prop: '{prop}',\n" +
            "                    type: 'select',\n" +
            "                    search: {search},\n" +
            "                    searchSpan: 5,\n" +
            "                    overHidden: true,\n" +
            "                    dicData: this.dict.get({dictCode}),\n" +
            "                },\n";


    /**
     * 字典（多选） {label}  {prop} {search} ,
     */
    String TEXT_DICT_CHECKBOX = "                {\n" +
            "                    label: '{label}',\n" +
            "                    prop: '{prop}',\n" +
            "                    type: 'select',\n" +
            "                    search: {search},\n" +
            "                    searchSpan: 5,\n" +
            "                    overHidden: true,\n" +
            "                    dataType: 'string',\n" +
            "                    dicData: this.dict.get({dictCode}),\n" +
            "                },\n";


    /**
     * 图片,支持多图/单图） {label}  {prop}
     */
    String IMG = "                {\n" +
            "                    label: '{label} ',\n" +
            "                    prop: '{prop}',\n" +
            "                    search: false,\n" +
            "                    overHidden: true,\n" +
            "                    html: true,\n" +
            "                    formatter: (val) => {\n" +
            "                        if(val.{prop} == null || val.{prop} == ''){\n" +
            "                            return \"\";\n" +
            "                        }else{\n" +
            "                            let imgs = val.{prop}.split(\",\");\n" +
            "                            let html = \"\"; \n" +
            "                            imgs.forEach(item => html += \"<img src='\" + item + \"'  style='border-radius: 40px;height: 40px;width: 40px;margin-top: 10px'>\")\n" +
            "                            return html;\n" +
            "                        }\n" +
            "                    }\n" +
            "                },\n";


}
