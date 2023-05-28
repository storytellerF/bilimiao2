package com.a10miaomiao.bilimiao.comm.store.model


import java.util.Calendar
import java.util.Date

class DateModel {
    private var year = 2009
    private var month = 1
    private var date = 1

    fun setValue(str: String) {
        val length = str.length
        year = str.substring(0, length - 4).toInt()
        month = str.substring(length - 4, length - 2).toInt()
        date = str.substring(length - 2, length).toInt()
    }

    fun getValue(span: String = "") = "$year$span${fillZero(month)}$span${fillZero(date)}"

    private fun getDate() = Calendar.getInstance().apply {
        set(year - 1900, month - 1, date)
    }

    fun setDate(date: Date): DateModel {
        val calendar = Calendar.getInstance().apply {
            time = date
        }
        year = calendar.get(Calendar.YEAR) + 1900
        month = calendar.get(Calendar.MONTH) + 1
        this.date = calendar.get(Calendar.DAY_OF_MONTH)
        return this
    }

    fun diff(value: DateModel) = when {
        value.year != year -> true
        value.month != month -> true
        value.date != date -> true
        else -> false
    }

//        fun save(context: Context, type: String) {
//            val time = year.toString() + fillZero(month) + fillZero(date)
//            SettingUtil.putString(context, type, time)
//        }

    private fun fillZero(i: Int): String {
        return if (i < 10) "0$i" else i.toString()
    }

    /**
     * 根据月份获取 TimeFrom
     */
    fun getTimeFromByMonth(): DateModel {
        return DateModel().let {
            it.year = year
            it.month = month
            it.date = date
            it
        }
    }

    /**
     * 根据月份获取 TimeTo
     */
    fun getTimeToByMonth(): DateModel {
        return DateModel().let {
            it.year = year
            it.month = month
            it.date = getMonthDate()
            it
        }
    }

    /**
     * 获取某一月份的天数
     */
    private fun getMonthDate(isLeapYear: Boolean, month: Int): Int {
        if (month in 1..12) {
            val dates =
                intArrayOf(31, if (isLeapYear) 29 else 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
            return dates[month - 1]
        }
        return 30

    }

    fun getMonthDate(): Int {
        return getMonthDate(year % 4 == 0, month)
    }

    /**
     * 根据获取gapCount天后的时间
     */
    fun getTimeByGapCount(gapCount: Int): DateModel {
        val calendar = Calendar.getInstance()
        calendar.set(year - 1900, month - 1, date)
        calendar.add(Calendar.DATE, gapCount)//参数-，换为1则为加1代表在原来时间的基础上减少一天一天;ps:加减月数 小时同加减天数
        return DateModel().setDate(calendar.time)
    }

    /**
     * 计算时间间隔
     */
    fun getGapCount(date: DateModel): Int {
        val startL = getDate().time.time
        val endL = date.getDate().time.time
        return ((endL - startL) / (1000 * 60 * 60 * 24)).toInt()
    }

    fun set(newDate: DateModel) {
        year = newDate.year
        month = newDate.month
        date = newDate.date
    }

    fun copy(): DateModel {
        return DateModel().let {
            it.year = year
            it.month = month
            it.date = date
            it
        }
    }

}