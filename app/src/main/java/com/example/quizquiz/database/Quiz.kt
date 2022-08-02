package com.example.quizquiz.database

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters

//Entity클래스는 표 즉. 데이터 베이스와 관련된 클래스
@Entity(tableName = "quiz")
data class Quiz(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    //퀴즈의 종류(OX, N지선다)
    var type : String?,
    // 발문
    var question : String?,
    //정답
    var answer : String?,
    //퀴즈의 카테고리
    var category: String?,
    //N지선다 문제의 선택지
    @TypeConverters(StringListTypeConverter::class)
    var guesses: List<String>? = null
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(type)
        parcel.writeString(question)
        parcel.writeString(answer)
        parcel.writeString(category)
        parcel.writeStringList(guesses)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Quiz> {
        override fun createFromParcel(parcel: Parcel): Quiz {
            return Quiz(parcel)
        }

        override fun newArray(size: Int): Array<Quiz?> {
            return arrayOfNulls(size)
        }
    }

}

class StringListTypeConverter{
    //객체에서 테이블로 갈때 호출
    @TypeConverter
    fun stringListToString(stringList: List<String>?):String?{
        return stringList?.joinToString(",")
    }
    @TypeConverter
    //테이블에서 객체로 갈때 호출
    fun stringtoStringList(string: String?): List<String>?{
        return string?.split(",")?.toList()
    }
}
