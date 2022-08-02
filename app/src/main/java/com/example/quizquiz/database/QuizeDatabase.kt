package com.example.quizquiz.database

import android.content.Context
import androidx.room.*

//Data Access Object : 데이터베이스에 데이터를 삽입하고 수정하는 등의 역할을 하는 것 Dao
@Dao
interface QuizDAO{
    @Insert
    fun insert(quiz: Quiz): Long
    @Update
    fun update(quiz: Quiz)
    @Delete
    fun delete(quiz: Quiz)
    @Query("SELECT * FROM quiz")
    fun getAll(): List<Quiz>
}

//메모리 낭비를 방지하기 위해 하나의 객체를 데이터 베이스에 접근 사용 가능할 수 있도록 한다.
@Database(entities=[Quiz::class], version=1)
@TypeConverters(StringListTypeConverter::class)
abstract class QuizDatabase : RoomDatabase() {
    abstract fun quizDAO(): QuizDAO

    // 정적 메서드(클래스 메서드)
    companion object {
        private var INSTANCE: QuizDatabase? = null

        //싱글턴 패턴
        fun getInstance(context: Context): QuizDatabase {
            if(INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    QuizDatabase::class.java, "database.db").build()
            }
            return INSTANCE!!
        }
    }
}

