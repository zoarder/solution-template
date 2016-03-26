package com.tigerit.exam;

import static com.tigerit.exam.IO.readLine;
import static com.tigerit.exam.IO.readLineAsInteger;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Pattern;

import com.tigerit.exam.TableData;

/**
 * All of your application logic should be placed inside this class. Remember we
 * will load your application from our custom container. You may add private
 * method inside this class but, make sure your application's execution points
 * start from inside run method.
 */
public class Solution implements Runnable {
	@Override
	public void run() {
		// // your application entry point
		//
		// // sample input process
		// String string = readLine();
		//
		// Integer integer = readLineAsInteger();
		//
		// // sample output process
		// printLine(string);
		// printLine(integer);

		Scanner input = new Scanner(System.in);
		TreeMap<String, TableData> tables;
		TreeMap<String, TableData> tablesShort;
		ArrayList<TableData> results;

		int testCases = input.nextInt();

		for (int T = 1; T <= testCases; T++) {
			tables = new TreeMap<>();
			results = new ArrayList<>();

			int nTables = input.nextInt();
			for (int i = 1; i <= nTables; i++) {
				TableData table = new TableData();
				String tableName = input.next();
				table.setTableName(tableName);

				int nColumns = input.nextInt();
				table.setnColumn(nColumns);

				int nRows = input.nextInt();
				table.setnRow(nRows);

				ArrayList<String> columnNames = new ArrayList<>();
				for (int j = 0; j < nColumns; j++) {
					String columnName = input.next();
					columnNames.add(columnName);
				}
				table.setColumnNames(columnNames);

				int tableData[][] = new int[101][101];
				for (int j = 0; j < nRows; j++) {
					for (int k = 0; k < nColumns; k++) {
						int cellData = input.nextInt();
						tableData[j][k] = cellData;
					}
				}
				table.setData(tableData);
				// System.out.println(table.toString());
				tables.put(tableName, table);
			}

			int nQueries = input.nextInt();
			input.nextLine();
			for (int i = 1; i <= nQueries; i++) {
				tablesShort = new TreeMap<>();
				TableData result = new TableData();

				String quaryLine1 = input.nextLine();
				// System.out.println(quaryLine1);
				String quaryLine2 = input.nextLine();
				// System.out.println(quaryLine2);
				String quaryLine3 = input.nextLine();
				// System.out.println(quaryLine3);
				String quaryLine4 = input.nextLine();
				// System.out.println(quaryLine4);

				String[] partsQuaryLine2 = quaryLine2.split(" ");
				String tableShortName1 = "";
				if (partsQuaryLine2.length > 2) {
					tableShortName1 = partsQuaryLine2[2];
					tablesShort.put(tableShortName1, tables.get(partsQuaryLine2[1]));
				} else {
					tableShortName1 = partsQuaryLine2[1];
					tablesShort.put(tableShortName1, tables.get(partsQuaryLine2[1]));
				}

				String[] partsQuaryLine3 = quaryLine3.split(" ");
				String tableShortName2 = "";
				if (partsQuaryLine3.length > 2) {
					tableShortName2 = partsQuaryLine3[2];
					tablesShort.put(tableShortName2, tables.get(partsQuaryLine3[1]));
				} else {
					tableShortName2 = partsQuaryLine3[1];
					tablesShort.put(tableShortName2, tables.get(partsQuaryLine3[1]));
				}

				String[] partsQuaryLine4 = quaryLine4.split(" ");
				String[] partsQuaryLine1 = quaryLine1.split(" ");

				String joinPredicate1 = partsQuaryLine4[1];
				String joinPredicate2 = partsQuaryLine4[3];
				// System.out.println(joinPredicate1);
				// System.out.println(joinPredicate2);
				String[] partsJoinPredicate1 = joinPredicate1.split("\\.");
				String[] partsJoinPredicate2 = joinPredicate2.split("\\.");

				// System.out.println(partsJoinPredicate1.length);
				// System.out.println(partsJoinPredicate2.length);

				int index1 = (tablesShort.get(partsJoinPredicate1[0]).getColumnNames()).indexOf(partsJoinPredicate1[1]);
				int index2 = (tablesShort.get(partsJoinPredicate2[0]).getColumnNames()).indexOf(partsJoinPredicate2[1]);
				int rowIndexes[] = new int[101];
				int minRow = 0;

				if (tablesShort.get(partsJoinPredicate1[0]).getnRow() < tablesShort.get(tableShortName2).getnRow()) {
					minRow = tablesShort.get(partsJoinPredicate1[0]).getnRow();
				} else {
					minRow = tablesShort.get(partsJoinPredicate2[0]).getnRow();
				}
				int data1[][] = tablesShort.get(partsJoinPredicate1[0]).getData();
				int data2[][] = tablesShort.get(partsJoinPredicate2[0]).getData();
				int k = 0;
				for (int j = 0; j < minRow; j++) {
					if (data1[j][index1] == data2[j][index2]) {
						rowIndexes[k++] = j;
					}
				}
				result.setnRow(k);
				int resultData[][] = new int[101][101];
				data1 = tablesShort.get(tableShortName1).getData();
				data2 = tablesShort.get(tableShortName2).getData();

				if (partsQuaryLine1[1].equals("*")) {
					ArrayList<String> resultColumnNames = new ArrayList<>();
					resultColumnNames.addAll(tablesShort.get(tableShortName1).getColumnNames());
					resultColumnNames.addAll(tablesShort.get(tableShortName2).getColumnNames());
					result.setColumnNames(resultColumnNames);

					result.setnColumn(resultColumnNames.size());
					for (int j = 0; j < result.getnRow(); j++) {
						int l = 0;
						for (k = 0; k < tablesShort.get(tableShortName1).getnColumn(); k++) {
							resultData[j][l++] = data1[rowIndexes[j]][k];
						}
						for (k = 0; k < tablesShort.get(tableShortName2).getnColumn(); k++) {
							resultData[j][l++] = data2[rowIndexes[j]][k];
						}
					}

				} else {
					ArrayList<String> resultColumnNames = new ArrayList<>();
					ArrayList<String> resultTableShortNames = new ArrayList<>();
					for (int j = 1; j < partsQuaryLine1.length; j++) {
						String str = partsQuaryLine1[j];
						// System.out.println(str);
						str = str.replaceAll(Pattern.quote(","), "");
						// System.out.println(str);
						String[] partsStr = str.split("\\.");
						// System.out.println(partsStr[0]);
						// System.out.println(partsStr[1]);
						resultTableShortNames.add(partsStr[0]);
						resultColumnNames.add(partsStr[1]);
					}
					result.setColumnNames(resultColumnNames);
					result.setnColumn(resultColumnNames.size());

					for (int j = 0; j < result.getnRow(); j++) {
						for (k = 0; k < resultTableShortNames.size(); k++) {
							int col = tablesShort.get(resultTableShortNames.get(k)).getColumnNames()
									.indexOf(resultColumnNames.get(k));
							data1 = tablesShort.get(resultTableShortNames.get(k)).getData();
							resultData[j][k] = data1[rowIndexes[j]][col];
						}
					}
				}
				SortFunc(resultData, result.getnRow(), result.getnColumn());
				result.setData(resultData);

				results.add(result);
				input.nextLine();
			}
			System.out.printf("Test: %d", T);
			System.out.println();
			for (int i = 0; i < results.size(); i++) {
				ArrayList<String> colName = results.get(i).getColumnNames();
				System.out.printf("%s", colName.get(0));
				for (int j = 1; j < colName.size(); j++) {
					System.out.printf(" %s", colName.get(j));
				}
				System.out.println();
				int data[][] = results.get(i).getData();

				for (int j = 0; j < results.get(i).getnRow(); j++) {
					System.out.printf("%d", data[j][0]);
					for (int k = 1; k < results.get(i).getnColumn(); k++) {
						System.out.printf(" %d", data[j][k]);
					}
					System.out.println();
				}
				System.out.println();
			}
		}

		input.close();
	}

	public void SortFunc(int arr[][], int rw, int clm) {
		int tem[] = new int[102];
		for (int i = 0; i < rw; i++) {
			for (int j = i + 1; j < rw; j++) {
				for (int k = 0; k < clm; k++) {
					if (arr[i][k] == arr[j][k])
						continue;
					if (arr[i][k] < arr[j][k]) {
						break;
					}
					for (int l = 0; l < clm; l++) {
						tem[l] = arr[i][l];
						arr[i][l] = arr[j][l];
						arr[j][l] = tem[l];
					}
					break;
				}
			}
		}
	}
}
