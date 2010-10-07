package com.scottbyrns.opencogo.point;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: scott
 * Date: Oct 6, 2010
 * Time: 3:47:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class PointFile {

    String file = null;
    PointMap pointMap = null;

    JSONObject pointFileJSON = null;

    public PointFile (String file) {
        this.file = file;
        openPointFile(this.file);
    }

    public void openPointFile (String file) {

        String json_point_file = null;

        try {
            json_point_file = readFileAsString(file);
        }
        catch (java.io.IOException e) {
            System.out.println("FILE READ ERROR");
        }

        if (null != json_point_file) {
            try {
                pointFileJSON = new JSONObject(json_point_file);
            }
            catch (JSONException e) {
                System.out.println("JSON ERROR");
            }
        }

    }



    public void savePointFile () {
        writeStringToPath(this.pointFileJSON.toString(), this.file);
    }

    public void savePointFile (String file) {
        writeStringToPath(this.pointFileJSON.toString(), file);
    }




    /**
	 * Open a file path and return the contents as a string.
	 * @param {String} filePath Path to the file to be opened.
	 * @since 1.0
	 */
	public static String readFileAsString(String filePath) throws java.io.IOException {
		BufferedInputStream file = null;
		byte[] buffer = new byte[(int) new File(filePath).length()];
		BufferedInputStream f = null;
		try {
			file = new BufferedInputStream(new FileInputStream(filePath));
			file.read(buffer);
		}
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
			if (null != file) {
				try {
					file.close();
				}
				catch (IOException ignored) {

				}
			}
		}
		return new String(buffer);
	}


    /**
     * Write a string to a file path.
     * @param fileContents
     * @param outputPath
     * @return
     */
    public static boolean writeStringToPath (String fileContents, String outputPath) {
        BufferedWriter out = null;
		try {
			out = new BufferedWriter(new java.io.FileWriter(outputPath));
			out.write(fileContents);

			return true;
		}
		catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
			return false;
		}
        finally {
            try {
                out.close();
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
	}

}
