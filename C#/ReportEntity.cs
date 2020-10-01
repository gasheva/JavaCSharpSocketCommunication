using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Text.Json;
using System.Text.Json.Serialization;

namespace ExcelReportWriter
{
    [Serializable]
    class ReportEntity
    {
        private String header;
        private List<String> tableHeaders;
        private List<MessagePartClass> messageParts;
        public string Header { get => header; set => header = value; }
        public List<string> TableHeaders { get => tableHeaders; set => tableHeaders = value; }
        public List<MessagePartClass> MessageParts { get => messageParts; set => messageParts = value; }
    }
    [Serializable]
    public class MessagePartClass
    {
        private LocalDate checkDate = new LocalDate();
        private String checkerName;
        private bool needRest;
        private int paintId;
        private String paintName;
        private String artistFio;
        private String gallery;

        public MessagePartClass() {}

        public string CheckerName { get => checkerName; set => checkerName = value; }
        public bool NeedRest { get => needRest; set => needRest = value; }
        public int PaintId { get => paintId; set => paintId = value; }
        public string PaintName { get => paintName; set => paintName = value; }
        public string ArtistFio { get => artistFio; set => artistFio = value; }
        public string Gallery { get => gallery; set => gallery = value; }
        [JsonPropertyName("checkDate")]
        public LocalDate CheckDate { get => checkDate; set => checkDate = value; }
    }
    [Serializable]
    public class LocalDate
    {
        private int year;
        private int month;
        private int day;

        [JsonPropertyName("year")]
        public int Year { get => year; set => year = value; }
        public int Month { get => month; set => month = value; }
        public int Day { get => day; set => day = value; }

        public LocalDate() { }

    };


}
