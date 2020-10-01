using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Excel = Microsoft.Office.Interop.Excel;
using Word = Microsoft.Office.Interop.Word;

namespace ExcelReportWriter
{
    class ReportWriter
    {
        public ReportWriter() { }
        public bool write(ReportEntity report)
        {
            object misValue = System.Reflection.Missing.Value;
            Excel.Application xlApp;
            Excel.Workbook xlWorkBook;
            Excel.Worksheet xlWorkSheet;
            try
            {
                xlApp = new Excel.Application();
                xlWorkBook = xlApp.Workbooks.Add(misValue);
                xlWorkSheet = (Excel.Worksheet)xlWorkBook.Worksheets.Add();
            }
            catch (Exception e)
            {
                Console.WriteLine("Ошибка создания excel файла");
                Console.WriteLine(e.Message);
                return false;
            }

            //пишем в Excel
            bool isWrote = write(report, xlWorkSheet);
            //xlWorkSheet.Cells[0, 0] = "1";
            xlApp.Visible = true;
            return isWrote;
            //return true;

        }
        private bool write(ReportEntity report, Excel.Worksheet xlWorkSheet)
        {
            try
            {
                //заголовок
                xlWorkSheet.Range[xlWorkSheet.Cells[1, 1], xlWorkSheet.Cells[1, 7]].Merge();
                xlWorkSheet.Cells[1, 1].Style.HorizontalAlignment = Microsoft.Office.Interop.Excel.XlHAlign.xlHAlignCenter;
                xlWorkSheet.Cells[1, 1].EntireRow.Font.Bold = true;
                xlWorkSheet.Cells[1, 1] = report.Header;

                //заголовки столбцов
                for (int i = 0; i<report.TableHeaders.Count; i++)
                {

                    xlWorkSheet.Cells[2, i+1] = report.TableHeaders[i];
                }
                //контент
                for (int i = 0; i < report.MessageParts.Count; i++)
                {

                    xlWorkSheet.Cells[i + 3, 1] = report.MessageParts[i].CheckDate.Day + "." + report.MessageParts[i].CheckDate.Month + "." + report.MessageParts[i].CheckDate.Year;
                    xlWorkSheet.Cells[i + 3, 2] = report.MessageParts[i].CheckerName;
                    xlWorkSheet.Cells[i + 3, 3] = report.MessageParts[i].NeedRest?"Да":"Нет";
                    xlWorkSheet.Cells[i + 3, 4] = report.MessageParts[i].PaintId;
                    xlWorkSheet.Cells[i + 3, 5] = report.MessageParts[i].PaintName;
                    xlWorkSheet.Cells[i + 3, 6] = report.MessageParts[i].ArtistFio;
                    xlWorkSheet.Cells[i + 3, 7] = report.MessageParts[i].Gallery;
                }
                xlWorkSheet.Columns.AutoFit();
            }
            catch (Exception e)
            {
                Console.WriteLine("Ошибка записи в файл");
                Console.WriteLine(e.Message);
                return false;
            }
            return true;
        }

    }
}
