using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Net;
using System.Net.Sockets;
using System.IO;
using System.Web.Script.Serialization;
using System.Text.Json;

namespace ExcelReportWriter
{
    class App
    {
        public static void Main(string[] args)
        {
            string input;
            bool output = true;
            //using (TcpClient tcpClient = new TcpClient("localhost", 6666))
	    using (TcpClient tcpClient = new TcpClient("192.168.100.8", 6666))
            {
                using (NetworkStream networkStream = tcpClient.GetStream())
                {
                    StreamReader sr = new StreamReader(networkStream);
                    input = sr.ReadLine();
                    Console.WriteLine(input);
                    var options = new JsonSerializerOptions
                    {
                        PropertyNameCaseInsensitive = true
                    };
                    try
                    {
                        ReportEntity report = JsonSerializer.Deserialize<ReportEntity>(input, options);
                        Console.WriteLine(report.MessageParts[0].CheckDate.Year);
                        ReportWriter reportWriter = new ReportWriter();
                        output = reportWriter.write(report);
                    }
                    catch(Exception e)
                    {
                        Console.WriteLine(e.Message);
                        output = false;
                    }


                    StreamWriter sw = new StreamWriter(networkStream);
                    sw.WriteLine(output.ToString());
                    sw.Flush();

                    sr.Close();
                    sw.Close();


                }
            };
            Console.ReadKey();
        
        }

        
    }
}
