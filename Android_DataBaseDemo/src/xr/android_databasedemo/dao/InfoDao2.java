package xr.android_databasedemo.dao;

/*
 * ���ݿ����ɾ�Ĳ�ڶ��ַ�ʽ
 * 
 *SQLiteDataBase�еķ��� 
 * */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import xr.android_databasedemo.bean.InfoBean;
import xr.android_databasedemo.db.XrSQLiteOpenHelper;

public class InfoDao2 {

	private XrSQLiteOpenHelper helper;

	public InfoDao2(Context context) {
		helper = new XrSQLiteOpenHelper(context);
	}

	// ��������
	public boolean addInfo(InfoBean bean) {

		SQLiteDatabase db = helper.getWritableDatabase();

		// ����һ������ ����������
		ContentValues values = new ContentValues();
		values.put("name", bean.name);
		values.put("phone", bean.phone);

		// ��һ������ �� ���ݿ����� �ڶ������� Ĭ��Ϊ�� ���������� ��ֵ��
		// ����ֵ �� Long ���� -1 ������ʧ�� ������Ϊ�ɹ�
		long addResult = db.insert("info", null, values);
		db.close();

		if (addResult != -1) {
			return true;
		} else {
			return false;
		}

	}

	// ɾ������
	public int delInfo(String name) {

		SQLiteDatabase db = helper.getReadableDatabase();

		// ��һ������ ���ݿ����� �ڶ������� ռλ�� ���������� �ַ����� ռλ���е�ֵ
		// ����ֵ int ��ʾ�����ܵ�Ӱ��
		int delResult = db.delete("info", "name = ?", new String[] { name });

		db.close();

		return delResult;
	}

	// ��������
	public int updateInfo(InfoBean bean) {
		SQLiteDatabase db = helper.getReadableDatabase();
		// ����һ������ ����������
		ContentValues values = new ContentValues();
		values.put("phone", bean.phone);

		// ��һ������ ���ݿ����� �ڶ������� �����е����� ���������� ռλ�� ���ĸ����� ռλ���е�ֵ
		// ����ֵ int ��ʾ�����ܵ�Ӱ��
		int updateResult = db.update("info", values, "name = ?", new String[] { bean.name });
		db.close();

		return updateResult;

	}

	// ��������
	public void checkInfo(String name) {

		SQLiteDatabase db = helper.getReadableDatabase();

		// ��һ������ ���ݿ����� �ڶ������� Ĭ��Ϊ�� ��ʾ��������Ԫ�� ���������� ռλ�� ���ĸ����� ռλ���е�ֵ
		// ��������� ����ʲô���� ���������� �������� ���߸����� ����ʲô˳������
		// ����ֵ ��һ���α�
		Cursor cursor = db.query("info", new String[] { "_id", "name", "phone" }, "name = ?", new String[] { name },
				null, null, "_id desc");

		// ����α겻Ϊ�� ������������0
		if (cursor != null && cursor.getCount() > 0) {

			// �����ƶ��α� ������һ��
			while (cursor.moveToNext()) {
				// �õ��α������е� ÿһ�е���ֵ
				int id = cursor.getInt(0);
				String namestr = cursor.getString(1);
				String phone = cursor.getString(2);
				System.out.println("_id:" + id + ";name:" + namestr + ";phone:" + phone);
			}

			// �ر��α�
			cursor.close();

		}

		db.close();

	}
}