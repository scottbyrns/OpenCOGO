package com.scottbyrns.opencogo.point;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.Date;

/**
 * Class to hold and manage a point file in JSON form.
 * User: scott
 * Date: Oct 6, 2010
 * Time: 8:47:01 PM
 */
public class PointFile {

    File file = null;
    PointMap pointMap = null;

    JSONObject pointFileJSON = null;

    /**
     * Create a new point file object from the file.
     * @param file
     */
    public PointFile (File file) {
        this.file = file;
        openPointFile(this.file);
    }
    /**
     * Open the point file as JSON from the specified file path.
     * @param file Path to the file to be opened.
     */
    public void openPointFile (File file) {

        String json_point_file = null;

        try {
            json_point_file = readFileAsString(file.getAbsolutePath());
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


    /**
     * Save the JSON point file to the location it was opened from.
     */
    public void savePointFile () {
        savePointFile(this.file.getAbsolutePath());
    }

    /**
     * Save the JSON point file to the specified locaiton.
     * @param file Location to save the JSON point file to.
     */
    public void savePointFile (String file) {
        setModifyDate();
        writeStringToPath(this.pointFileJSON.toString(), file);
    }

    /**
     * Set the "modified" attribute in the JSON to the current date.
     */
    public void setModifyDate () {
        try {
            this.pointFileJSON.put("modified", new Date().getTime());
        }
        catch (JSONException e) {
            System.out.println("Trouble updating the modified date before a save.");
        }
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
