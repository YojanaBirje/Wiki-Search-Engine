/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * @author yojana
 */
public class merge_idtitle {
    
    private BufferedReader[] readers ;
    private Boolean[] isEmpty ;

    class Comp implements Comparator<key_posting> { 
        @Override
        public int compare(key_posting x, key_posting y) 
        { 
            return x.key.compareTo(y.key);
        } 
    }
     
    public void merge() {
	    try{
	        int no_temp_indexes/*=sax_parser.file_no*/,isnull=0;
	       no_temp_indexes=3528;
	        System.out.println("Merging it :"+no_temp_indexes+" Files");
	        
	        readers = new BufferedReader[no_temp_indexes+1];
	        isEmpty = new Boolean[no_temp_indexes+1];
	        Comparator<key_posting> compares = new Comp();

	        int no_entries=0,ter_entry=0,pri_entries=0;
	        

			for(int i=0;i<no_temp_indexes;i++){
		        readers[i] = new BufferedReader(new InputStreamReader(new FileInputStream("/media/yojana/New Volume1/indices/index_it"+i+".txt")));
		        isEmpty[i] = false;
			}
			File primary_index = new File("/media/yojana/New Volume1/indexes/titleindex.txt");
			File secondry_index = new File("/media/yojana/New Volume1/indexes/secondarytitleindex.txt");
		    File tertiary_index = new File("/media/yojana/New Volume1/indexes/tertiarytitleindex.txt");

        
			if (primary_index.exists()==false) 
		            primary_index.createNewFile();
			
			if (secondry_index.exists()==false) 
		            secondry_index.createNewFile();
		        
	        if (tertiary_index.exists()==false) 
	            tertiary_index.createNewFile();
        
			FileWriter fw_pri = new FileWriter("/media/yojana/New Volume1/indexes/titleindex.txt");
			FileWriter fw_sec = new FileWriter("/media/yojana/New Volume1/indexes/secondarytitleindex.txt");
		    FileWriter fw_ter = new FileWriter("/media/yojana/New Volume1/indexes/tertiarytitleindex.txt");
			BufferedWriter bw_pri = new BufferedWriter(fw_pri);
			BufferedWriter bw_sec = new BufferedWriter(fw_sec);
		    BufferedWriter bw_ter = new BufferedWriter(fw_ter);
		        
			System.out.println("Starting Merge of id:title");
	

	        String line="";
	        long sec_offset=0,ter_offset=0;
	        
	        PriorityQueue<key_posting> pq_entries =  new PriorityQueue<key_posting>(no_temp_indexes+1,compares); 
			
			for(int j=0;j<no_temp_indexes;++j)
			{
				if(!isEmpty[j])
				{
		                    
					line = readers[j].readLine();
					if(line!=null)
					{
		                            
						key_posting t = new key_posting();

		                    int k_p = line.indexOf(":");
		                    t.posting=line.substring(k_p+1);//k_p[1];
		                    t.temp_doc_no=j;
		                    t.key=line.substring(0,k_p);
		                    				pq_entries.add(t);
						++no_entries;
					}
					else{
						++isnull;
						isEmpty[j]=true;
						readers[j].close();
					}
							 
				}		 	 
		    }
        
	        key_posting k_p1 = pq_entries.poll();
	        int out_last = k_p1.temp_doc_no;
	        String out_posting = k_p1.posting,out_key = k_p1.key;
	        String lout_posting=null,lout_key=null;
        
        	while(isnull<no_temp_indexes){
	            if(isEmpty[out_last]){
	                key_posting ltop = pq_entries.poll();
	                out_last=ltop.temp_doc_no;
	                lout_posting=ltop.posting;
	                lout_key=ltop.key;
	               
	            }
	            else
	            {
	                line = readers[out_last].readLine();
	                if(line==null)
	                {
	                    if(!isEmpty[out_last])
	                        isnull++;
	                    isEmpty[out_last]=true;    
	                    readers[out_last].close();
	                }
	                else{
	                   
	                    key_posting t = new key_posting();
	                
	                   int k_p = line.indexOf(":");
	                    t.posting=line.substring(k_p+1);//k_p[1];
	                    t.temp_doc_no=out_last;
	                    t.key=line.substring(0,k_p);
	                    pq_entries.add(t);
	                    ++no_entries;
	                    
	                   //System.out.println("Finised entering");
	                    key_posting ltop1 = pq_entries.poll();

	                    lout_posting=ltop1.posting;
	                    lout_key=ltop1.key;
	                    out_last=ltop1.temp_doc_no;
	                }
	            }
	            if(lout_key.equals(out_key)&&out_key!=null)
	             {
	                // System.out.print("\tEqual case");
	                 out_posting=out_posting+lout_posting;
	             }
	             else
	             {
	                if(pri_entries%50==0)
	                {
	                    bw_ter.write(out_key+":"+ter_offset+"\n");
	                    bw_ter.flush();
	                }
	                 
	                //Write into secondary index
	                
	                bw_sec.write(out_key+":"+sec_offset+"\n");
	                bw_sec.flush();
	                ter_offset+=out_key.getBytes().length+String.valueOf(sec_offset).getBytes().length+2;
	                
	                bw_pri.write(out_key+":"+out_posting+"\n");
	                bw_pri.flush();
	                sec_offset+=out_key.getBytes().length+out_posting.getBytes().length+2;
	                pri_entries++;
	                //System.out.println("next is: "+lout_word+"last was"+out_word);
	                  

	                out_key=lout_key;
	                out_posting=lout_posting;
	                lout_key="";
	                lout_posting="";
	                
            	}
        	}
            bw_pri.close();
            bw_sec.close();
            bw_ter.close();
            fw_pri.close();
            fw_sec.close();
            fw_ter.close();
          	System.out.print(" Records Written into merged it: "+no_entries+"\n");	 	        
        }catch(IOException e){
            System.out.print(" Error in merging the it ");	 	
		}
    }
}