package com.scottbyrns.opencogo.point;

import org.json.JSONArray;
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

    public static final String CREATED = "c";
    public static final String MODIFIED = "m";
    public static final String AUTHOR = "a";

    public static final String POINTS = "p";
    public static final String NAME = "n";
    public static final String DESCRIPTION = "d";
    public static final String NOTES = "nt";

    public static final String X = "x";
    public static final String Y = "y";
    public static final String Z = "z";

    File file = null;
    public PointMap pointMap = null;

    JSONObject pointFileJSON = null;

    /**
     * Create a new point file object from the file.
     * @param file
     */
    public PointFile (File file) {
        this.pointMap = new PointMap();
        this.file = file;
        openPointFile(this.file);
        inflatePointFile();
    }

    /**
     * Inflate the PointMap with data from our source file.
     */
    public void inflatePointFile () {
        JSONArray points = null;
        
        try {
            points = this.pointFileJSON.getJSONArray(PointFile.POINTS);
        }
        catch (JSONException e) {
            System.out.println("Error reading points object from file.");
        }

        JSONObject point;

        for (int i = 0; i < points.length(); i += 1) {
            try {
                point = points.getJSONObject(i);
                float x, y, z;
                x = (float)point.getDouble(PointFile.X);
                y = (float)point.getDouble(PointFile.Y);
                z = (float)point.getDouble(PointFile.Z);
                pointMap.addPoint(point.getString(PointFile.NAME), new Point(x, y, z));
            }
            catch (JSONException e) {
                e.printStackTrace();
                System.out.println("Point corupt?");
            }
        }
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
            this.pointFileJSON.put(PointFile.MODIFIED, new Date().getTime());
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
