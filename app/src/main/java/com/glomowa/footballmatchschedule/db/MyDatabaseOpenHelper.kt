package com.glomowa.footballmatchschedule.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.glomowa.footballmatchschedule.model.Team
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context): ManagedSQLiteOpenHelper(ctx, "FavoritesEvent.db", null, 1){

    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null){
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(FavoriteEvent.TABLE_FAVORITE, true,
                FavoriteEvent.FAVORITE_ID to INTEGER+ PRIMARY_KEY+ AUTOINCREMENT,
                FavoriteEvent.EVENT_ID to TEXT,
                FavoriteEvent.HOME_ID to TEXT,
                FavoriteEvent.AWAY_ID to TEXT,
                FavoriteEvent.AWAY_TEAM to TEXT,
                FavoriteEvent.HOME_TEAM to TEXT,
                FavoriteEvent.LEAGUE to TEXT)
        db.createTable(FavoriteTeam.TABLE_TEAM, true,
                FavoriteTeam.FAVORITE_ID to INTEGER+ PRIMARY_KEY+ AUTOINCREMENT,
                FavoriteTeam.TEAM_ID to TEXT,
                FavoriteTeam.TEAM_NAME to TEXT,
                FavoriteTeam.COUNTRY to TEXT,
                FavoriteTeam.TEAM_BADGE to TEXT,
                FavoriteTeam.STADIUM to TEXT,
                FavoriteTeam.FORMED_YEAR to TEXT,
                FavoriteTeam.DESCRIPTION to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropIndex(FavoriteEvent.TABLE_FAVORITE, true)
        db.dropIndex(FavoriteTeam.TABLE_TEAM, true)
    }
}

val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)