package io.github.wslxm.springbootplus2.core.utils;

import lombok.extern.slf4j.Slf4j;

/***
 * log.error 和log 输出颜色
 * @author 王松
 * @mail 1720696548@qq.com
 * @date 2020/7/7 0007 9:00
 */
@Slf4j
public class ConsoleColors {

    /**
     * Reset 重置颜色
     */
    public static final String RESET = "\033[0m";
    /**
     * PURPLE
     */
    public static final String PURPLE = "\033[0;35m";

    public static void main(String[] args) {
        getSuccessPurple();
    }

    /**
     *  启动成功-紫色
     */
    public static void getSuccessPurple() {
        log.info(ConsoleColors.PURPLE + "\r\n" +
                "         ####                #             #  ##               ##  \n" +
                "     #########        ##### ##             #               #   ##  \n" +
                "      ##   ##        ####   #####          ####        ######  # ##\n" +
                "      #    ##            #########    #######          ############\n" +
                "     ########       ####### ## ##     ###   #  #         ## ####  #\n" +
                "     ##            #####    #  ##      #    ## ##        ##   ##  #\n" +
                "     ##               # #  ##  ##      # ### ###         ###  #   #\n" +
                "     ##########      ##### ##  ##      ## ## ###       ####  ##  ##\n" +
                "    ## ###  ###     #### ###   #      ##  ## ##       ###   ##   ##\n" +
                "    ## ##   ##      ##    ##  ##      ##  # ####            ##   ##\n" +
                "   ##  ##  ##            ## ####     ##  ## #  ## #        ##  ### \n" +
                "  ##   #######          ##   ##      #   #      ###       #    ### \n" +
                "                             #                   ##                "
                + ConsoleColors.RESET);
    }
}
