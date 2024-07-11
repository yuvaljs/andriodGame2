package aaaa.dfs.model;

import java.util.ArrayList;

public class RecordList {

    private ArrayList<Record> recordList = new ArrayList<Record>();
    private int numOfRecord = 0;
    private final int MAX_NUM_OF_RECORD = 10;


    public RecordList() {

    }

    public ArrayList<Record> getRecordList() {
        return recordList;
    }

    public RecordList setRecordList(ArrayList<Record> recordList) {
        this.recordList = recordList;
        return this;
    }

    public int getNumOfRecord() {
        return numOfRecord;
    }

    public RecordList setNumOfRecord(int numOfRecord) {
        this.numOfRecord = numOfRecord;
        return this;
    }

    public int getMAX_NUM_OF_RECORD() {
        return MAX_NUM_OF_RECORD;
    }


    public RecordList addRecord(Record record)
    {
        if(numOfRecord == 0 )
        {
            recordList.add(record);
            numOfRecord++;
        }

        else
        {
            if ((numOfRecord<MAX_NUM_OF_RECORD)&&(record.getScore()<recordList.get(numOfRecord-1).getScore()))
            {
                recordList.add(numOfRecord++,record);
            }
            else
            {
                for(int i =0  ;i <numOfRecord ;i++)
                {
                    if (recordList.get(i).getScore()<=record.getScore())
                    {
                        if (numOfRecord == MAX_NUM_OF_RECORD)
                            recordList.remove(MAX_NUM_OF_RECORD-1);
                        else numOfRecord++;
                        recordList.add(i,record);
                        break;
                    }

                }
            }

        }

        return  this;
    }


    @Override
    public String toString() {
        return "RecordList{" +
                "recordList=" + recordList.toString() +
                ", numOfRecord=" + numOfRecord +
                '}';
    }
}
