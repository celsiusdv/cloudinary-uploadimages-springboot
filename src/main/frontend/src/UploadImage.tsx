import axios from "axios";
import { useState } from "react";

type ImageObject = {
    src: string;
    alt: string;
    file: File;
};
const UploadImage = () => {
    const [selectedImages, setSelectedImages] = useState<ImageObject[]>([]);
    //http://localhost:8080/api/upload-image/upload
    const handleImage = (e: React.ChangeEvent<HTMLInputElement>) => {
        const files: File[] = Array.from(e.target.files || []);
        const imagesArray: ImageObject[] = files.map((file) => ({
            src: URL.createObjectURL(file),
            alt: file.name,
            file: file,
        }));// todo: add delete image functionality
        setSelectedImages([...selectedImages, ...imagesArray]);
    };
    const handleUpload = async () => {
        if (!selectedImages) {
            console.error('No file selected');
            return;
        }
        const formData = new FormData();
        selectedImages.forEach(image =>{
            formData.append('image', image.file);
            console.log(formData.get)
        });
        try {
            const response = await axios.post('http://localhost:8080/api/upload-image/upload', formData, {
                headers: {
                    'Content-Type': 'multipart/form-data',
                },
            });
            console.log('File upload successful:', response.data);
        } catch (error) {
            console.error('Error uploading file:', error);
        }
    };

    return (
        <div>
            <input type='file' accept='image/*' id="files" onChange={handleImage} multiple />
            <button onClick={handleUpload}>Upload</button>
            {selectedImages.map((image, index) => (
                <div key={index}>
                    <img src={image.src} alt={image.alt} width={70} />
                </div>
            ))}
        </div>
    );
};

export default UploadImage;