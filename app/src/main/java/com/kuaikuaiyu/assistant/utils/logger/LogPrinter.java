package com.kuaikuaiyu.assistant.utils.logger;

import android.support.annotation.NonNull;
import android.support.v4.text.TextUtilsCompat;
import android.text.TextUtils;

import timber.log.Timber;

/**
 * Wrap {@link timber.log.Timber.Tree} for make log pretty
 */
public final class LogPrinter extends Timber.DebugTree {

    private static final int STACK_OFFSET = 8;
    private static final int LINE_LENGTH = 1000;

    private static final String TOP_BORDER = "╔══════════════════════════════════════════════════════";
    private static final String BOTTOM_BORDER = "╚══════════════════════════════════════════════════════";
    private static final String MIDDLE_BORDER = "║──────────────────────────────────────────────────────";

    private static final String PREFIX_BORDER = "║ ";

    /**
     * 因为如果设置了tag，那么会在timber中多走一个方法，方法栈会发生变化，造成不准确的情况。
     */
    private boolean isCustomTag = true;

    private StringBuilder sb = new StringBuilder();

    public Settings getSettings() {
        return settings;
    }

    private final Settings settings;

    private static final String PROPERTY = System.getProperty("line.separator");

    LogPrinter(Settings settings) {
        this.settings = settings;
    }

    /**
     * rule for auto tag
     */
    @Override
    protected String createStackElementTag(StackTraceElement ignored) {
        isCustomTag = false;
        int offset = STACK_OFFSET + settings.methodOffset - 1;
        return super.createStackElementTag(new Throwable().getStackTrace()[offset]);
    }

    protected void log(int priority, String tag, @NonNull String message, Throwable ignored) {
        String[] lines = message.split(PROPERTY);
        super.log(priority, tag, TOP_BORDER, null);
        super.log(priority, tag, PREFIX_BORDER + getTail(), null);
        super.log(priority, tag, MIDDLE_BORDER, null);
        for (int i = 0, length = lines.length; i < length; i++) {
            if (lines[i].length() < LINE_LENGTH) {
                super.log((priority), tag, PREFIX_BORDER + lines[i], null);
            } else {//把数据分成多行显示
                try {
                    String tmpStr;
                    int j = lines[i].length() / LINE_LENGTH;
                    for (int k = 0; k < j; k++) {
                        tmpStr = lines[i].substring(LINE_LENGTH * k, LINE_LENGTH * (k + 1));
                        super.log((priority), tag, PREFIX_BORDER + tmpStr, null);
                    }
                    //打印最后一行
                    tmpStr = lines[i].substring(LINE_LENGTH * j, lines[i].length());
                    if (!TextUtils.isEmpty(tmpStr))
                        super.log((priority), tag, PREFIX_BORDER + tmpStr, null);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        // Finally print bottom line
        super.log((priority), tag, BOTTOM_BORDER, null);

        isCustomTag = true;
    }

    /**
     * ==> onCreate(MainActivity.java:827) Thread:main
     */
    private String getTail() {
        if (!settings.showMethodLink) {
            return "";
        }

        int index = STACK_OFFSET + settings.methodOffset + 1;
        if (isCustomTag) {
            index -= 2;
        }
        final StackTraceElement stack = Thread.currentThread().getStackTrace()[index];

        if (sb.length() < 0) {
            sb = new StringBuilder();
        } else {
            sb.setLength(0);
        }

        sb.append(String.format("CIGK™   %s(%s:%s)",
                stack.getMethodName(),
                stack.getFileName(),
                stack.getLineNumber()));

        if (settings.showThreadInfo) {
            sb.append(" Thread: ").append(Thread.currentThread().getName()); // Thread:main
        }
        return sb.toString();
    }

    /**
     * 根据级别显示log
     *
     * @return 默认所有级别都显示
     */
    @Override
    protected boolean isLoggable(int priority) {
        return priority >= settings.priority;
    }

}
