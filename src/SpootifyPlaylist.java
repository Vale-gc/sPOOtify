import java.util.List;
import java.util.LinkedList;

public class SpootifyPlaylist extends SpootifyContent {
    private List<SpootifyContent> contentList;
    
    public SpootifyPlaylist(String title){
        super(title, 0);
        contentList = new LinkedList<SpootifyContent>();
    }

    public List<SpootifyContent> getContentList() {
        return this.contentList;
    }

    public void setContentList(List<SpootifyContent> contentList) {
        this.contentList = contentList;
    }

    @Override
    public String getType(){
        return "playlist";
    }

    public void addContent(SpootifyContent content){
        contentList.add(content);
        this.setDuration(this.getDuration() + content.getDuration());
    }

    public void removeContent(SpootifyContent content){
        if(contentList.contains(content))
            contentList.remove(content);
            this.setDuration(this.getDuration() - content.getDuration());
    }

}

